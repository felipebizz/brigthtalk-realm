package com.challenge.brightalk.service;

import com.challenge.brightalk.dao.RealmDAO;
import com.challenge.brightalk.mapper.OrikaBeanMapper;
import com.challenge.brightalk.model.Realm;
import com.challenge.brightalk.model.RealmDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class RealmServiceImpl implements RealmService {

    @Autowired
    OrikaBeanMapper orikaMapper;
    @Autowired
    private RealmDAO realmDAO;
    private Random random = new SecureRandom();

    /**
     * Key Number Generator
     *
     * @param min mininum number
     * @param max maximum number
     * @return ramdom key
     */
    private String generateRandomNumberInts(int min, int max) {
        return String.valueOf(random.ints(min, (max + 1)).findFirst().getAsInt());
    }

    @Transactional
    @Override
    public int save(RealmDTO realmDTO) {
        Realm realm = orikaMapper.map(realmDTO, Realm.class);
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
