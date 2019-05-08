package com.challenge.brightalk.service;

import com.challenge.brightalk.model.Realm;
import com.challenge.brightalk.model.RealmDTO;

import java.util.List;

public interface RealmService {

    int save(RealmDTO realmDTO);

    Realm getRealmById(final int Id);

    Realm getRealmByName(final String name);

    List<Realm> list();
}
