package com.challenge.brightalk.service;

import com.challenge.brightalk.model.Realm;

import java.util.List;

public interface RealmService {

    int save(Realm realm);

    Realm getRealmById(final int Id);

    Realm getRealmByName(final String name);

    List<Realm> list();
}
