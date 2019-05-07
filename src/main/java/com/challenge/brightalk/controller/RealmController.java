package com.challenge.brightalk.controller;

import com.challenge.brightalk.model.Error;
import com.challenge.brightalk.model.Realm;
import com.challenge.brightalk.service.RealmService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/service/user/realm")
public class RealmController {

    private static Logger log = Logger.getLogger(RealmController.class);

    @Autowired
    private RealmService realmService;

    @RequestMapping("/{name}")
    public ResponseEntity<?> getRealmByName(@RequestParam("name") String name) {
        if (log.isDebugEnabled())
            log.debug("Request Received for get Realm by Name " + name);
        final Realm realm = realmService.getRealmByName(name);
        if (realm == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(realm);
    }


    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<List<Realm>> getAllRealms() {
        return ResponseEntity.ok().body(realmService.list());
    }

    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> createRealm(@RequestBody Realm realm) {
        if (log.isDebugEnabled())
            log.debug("Request Received for create Realm ");

        if (realm.getName().isEmpty() || realm.getName() == null)
            return ResponseEntity.badRequest().body(new Error("InvalidRealmName"));

        if (realmService.getRealmByName(realm.getName()) != null)
            return ResponseEntity.badRequest().body(new Error("DuplicateRealmName"));

        final int uId = realmService.save(realm);
        return ResponseEntity.ok().body(realmService.getRealmById(uId));
    }

    @GetMapping(value = "/{realmId}",
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<?> getByIdRealm(@PathVariable("realmId") int realmId) {

        if (isNumeric(String.valueOf(realmId)))
            return ResponseEntity.badRequest().body(new Error("InvalidArgument"));

        Realm realmResponse = realmService.getRealmById(realmId);

        if (realmService.getRealmById(realmId) == null)
            return ResponseEntity.badRequest().body((new Error("RealmNotFound")));

        return ResponseEntity.ok().body(realmResponse);
    }

    /**
     * Check if parameter is numeric
     * @param strNum parameter
     * @return True or False
     */
    private static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }
}
