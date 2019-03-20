package com.phantom.quote.service;

import com.google.gson.JsonObject;
import com.phantom.logging.PhantomLogger;
import com.phantom.model.dao.QuoteRequestDao;
import com.phantom.model.entity.QuoteRequestEntity;
import com.phantom.model.entity.User;
import com.phantom.quote.request.QuoteRequest;
import com.phantom.user.service.UserService;
import com.phantom.util.PhantomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QService {

    @Autowired
    UserService userService;

    @Autowired
    QuoteRequestDao quoteRequestDao;

    PhantomLogger logger  = PhantomLogger.getLoggerObject(this.getClass());

    public String getQuote(QuoteRequest quoteRequest) {

        JsonObject jsonObject = new JsonObject();
        if (PhantomUtil.isNullOrEmpty(quoteRequest.getSsoToken())) {
            jsonObject.addProperty("status", 400);
            jsonObject.addProperty("msg", "User not logged in");
            return jsonObject.toString();
        }

        if (PhantomUtil.isNullOrEmpty(quoteRequest.getQuantity())) {
            jsonObject.addProperty("status", 400);
            jsonObject.addProperty("msg", "Product Quantity can not be blank");
            return jsonObject.toString();
        }
        if (PhantomUtil.isNullOrEmpty(quoteRequest.getProduct())) {
            jsonObject.addProperty("status", 400);
            jsonObject.addProperty("msg", "Strain Details can not be blank");
            return jsonObject.toString();
        }
        if (PhantomUtil.isNullOrEmpty(quoteRequest.getLocation())) {
            jsonObject.addProperty("status", 400);
            jsonObject.addProperty("msg", "User Location can not be blank");
            return jsonObject.toString();
        }
        User user = userService.getUserDetails(quoteRequest.getSsoToken());

        try{
            QuoteRequestEntity quoteRequestEntity = new QuoteRequestEntity();
            quoteRequestDao.saveOrUpdate(quoteRequestEntity);
        }catch (Exception e){

        }

        jsonObject.addProperty("status", 200);
        jsonObject.addProperty("msg", "Request Submitted");
        return jsonObject.toString();
    }

}
