package com.phantom.prop.change;

import com.phantom.logging.PhantomLogger;
import com.phantom.prop.templates.PropertyChangeListener;
import com.phantom.prop.templates.PropertyChangeNotifier;
import org.apache.commons.vfs2.*;
import org.apache.commons.vfs2.impl.DefaultFileMonitor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Hooks itself to VFS api, listens for file updates, notifies listeners after calling 
 * property manager's reload() method
 *  
 * @author nitin.goyal
 *
 */

@Component
public class AppPropertyChangePropagator implements FileListener, PropertyChangeNotifier {

	
	private Map<String,ReloadableProperty> reloadableProperties = new HashMap<String,ReloadableProperty>();
	private List<PropertyChangeListener> eventListeners = new LinkedList<PropertyChangeListener>();
	
    PhantomLogger logger = PhantomLogger.getLoggerObject(this.getClass());


	@Override
	public void addPropertyManager(ReloadableProperty properties) {
		reloadableProperties.put(properties.getPropertyFileName(), properties);
		hookVFS(properties.getPropertyFilePath());
	}

	@Override
    public void registerForChanges(PropertyChangeListener listener){
        eventListeners.add(listener);
    }
    
    @Override
    public void fileChanged(FileChangeEvent fileChangeEvent) {
    	
    	//Capture which property has been changed
    	String fileName = fileChangeEvent.getFile().getName().getBaseName();
        logger.info("Application properties file "+ fileName + " has been updated.");

        //Reload only this property
        reloadableProperties.get(fileName).reload();
        
        //But notify all (since there may be listeners not interested in properties 
        //but want to get dynamically reloaded 
        notifyListeners();
    }

    @Override
	public void notifyListeners() {
        for(PropertyChangeListener listener:eventListeners){
            listener.propertyChanged();
        }
	}

    @Override
    public void fileCreated(FileChangeEvent arg0) {}

    @Override
    public void fileDeleted(FileChangeEvent arg0) {}

    private void hookVFS(String propertyFileName)
    {
		logger.info("Hooking application file " + propertyFileName + " change notification with VFS");
		try {
			
			String pathToPropertyFile = new ClassPathResource(propertyFileName).getFile().getPath();
			FileSystemManager fsManager = VFS.getManager();
			FileObject listenFile = fsManager.resolveFile(pathToPropertyFile);

			DefaultFileMonitor fm = new DefaultFileMonitor(this);
			fm.setRecursive(true);
			fm.addFile(listenFile);
			fm.setDelay(15000);
			fm.start();
			
		} catch (FileSystemException ex) {
			logger.error("Falied to load application properties due to FileSystemException");
			
			//throw new CustomException("Failed to initialize properties due to file system exception", ex);
		} catch (IOException e) {
			logger.error("Falied to load application properties due to IOexceoption");
			//throw new CustomException("Failed to initialize properties due to file system exception", e);
		}
    }


}
