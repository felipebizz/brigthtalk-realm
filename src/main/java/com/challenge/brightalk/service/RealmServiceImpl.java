package com.challenge.brightalk.service;

import com.challenge.brightalk.dao.RealmDAO;
import com.challenge.brightalk.model.Realm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class RealmServiceImpl implements RealmService {


    @Autowired
    private RealmDAO realmDAO;

    /**
     * Key Number Generator
     *
     * @param min mininum number
     * @param max maximum number
     * @return ramdom key
     */
    private static String generateRandomNumberInts(int min, int max) {
        Random random = new Random();
        return String.valueOf(random.ints(min, (max + 1)).findFirst().getAsInt());
    }

    @Transactional
    @Override
    public int save(Realm realm) {
        realm.setKey(generateRandomNumberInts(1, 1000));
        return realmDAO.insert(realm);
    }

    @Override
    public Realm getRealmById(final int id) {
        return realmDAO.getRealmById(id);
    }

    @Override
    public Realm getRealmByName(final String name) {
        return realmDAO.getRealmByName(name);
    }

    @Override
    public List<Realm> list() {
        return realmDAO.getAll();
    }

}
