package com.challenge.brightalk.dao;

import com.challenge.brightalk.model.Realm;

import java.util.List;


public interface RealmDAO {

    List<Realm> getAll();

    Realm getRealmById(final int Id);

    Realm getRealmByName(final String name);

    int insert(Realm realm);

}
