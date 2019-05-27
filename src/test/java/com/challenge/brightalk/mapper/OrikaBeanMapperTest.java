package com.challenge.brightalk.mapper;


import com.challenge.brightalk.config.AppConfig;
import com.challenge.brightalk.model.Realm;
import com.challenge.brightalk.model.RealmDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class OrikaBeanMapperTest {

    @Autowired
    OrikaBeanMapper mapper;

    @Test
    public void Should_MapRealmDTO_When_GivenRealm() {

        // Given
        Realm realm = new Realm();
        realm.setKey("123");
        realm.setName("felipe");
        realm.setDescription("My description");

        // When
        RealmDTO realmDTO = mapper.map(realm, RealmDTO.class);

        // Then
        Assert.assertEquals(realmDTO.getKey(), "123");
        // Our UserUserDtoMapper told orika mapper how to populate the Name attribute.
        Assert.assertEquals(realmDTO.getName(), "felipe");
        // Our AddressToStringConverter told orika how to convert an Description object to a String.
        Assert.assertEquals(realmDTO.getDescription(), "My description");
    }

}
