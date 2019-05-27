package com.challenge.brightalk.controller;

import com.challenge.brightalk.model.Error;
import com.challenge.brightalk.model.Realm;
import com.challenge.brightalk.model.RealmDTO;
import com.challenge.brightalk.service.RealmService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/service/user/realm")
public class RealmController {

    private static final Logger log = Logger.getLogger(RealmController.class);

    @Autowired
    private RealmService realmService;


    /**
     * Check if parameter is numeric
     *
     * @param strNum parameter
     * @return True or False
     */
    private static boolean isNumeric(String strNum) {
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity getRealmByName(@RequestParam("name") String name) {
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
    public ResponseEntity<?> createRealm(@RequestBody RealmDTO realmDTO) {


        if (log.isDebugEnabled())
            log.debug("Request Received for create Realm ");

        if (realmDTO.getName().isEmpty() || realmDTO.getName() == null)
            return ResponseEntity.badRequest().body(new Error("InvalidRealmName"));

        if (realmService.getRealmByName(realmDTO.getName()) != null)
            return ResponseEntity.badRequest().body(new Error("DuplicateRealmName"));

        final int uId = realmService.save(realmDTO);
        return ResponseEntity.ok().body(realmService.getRealmById(uId));
    }

    @GetMapping(value = "/{realmId}",
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity getByIdRealm(@RequestBody Realm realm, @PathVariable("realmId") int realmId) {
        if (log.isDebugEnabled())
            log.debug("Request Received for Realm by Id");

        if (!isNumeric(String.valueOf(realm.getId())))
            return ResponseEntity.badRequest().body(new Error("InvalidArgument"));

        Realm realmResponse = realmService.getRealmById(realm.getId());
        if (null == realmResponse)
            return ResponseEntity.badRequest().body((new Error("RealmNotFound")));

        return ResponseEntity.ok().body(realmResponse);
    }
}
