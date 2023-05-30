package edu.upc.dsa;

import edu.upc.dsa.models.Item;
import edu.upc.dsa.models.User;
import edu.upc.dsa.util.QueryHelper;
import org.junit.Assert;
import org.junit.Test;

public class QueryHelperTest {


    @Test
    public void testQuerySELECTID1() {
       // Assert.assertEquals("SELECT ID FROM User WHERE mail=?",
         //       QueryHelper.createQuerySELECTID(new User("marin@hotmail.com", "mdolle", "12a")));
    }

    @Test
    public void testQuerySELECTID2() {
        //Assert.assertEquals("SELECT ID FROM Item WHERE name=?",
                //QueryHelper.createQuerySELECTID(new Item("sword", "cut your head", 10, 20, 90, "image.png")));
    }

    @Test
    public void testQueryUPDATE() {
       // System.out.println(QueryHelper.createQueryUPDATE(new Item("sword", "cut your head", 10, 20, 90, "image.png")));
    }

    @Test
    public void testQueryINSERT() {
        // System.out.println(QueryHelper.createQueryUPDATE(new Item("sword", "cut your head", 10, 20, 90, "image.png")));
        System.out.println(QueryHelper.createQueryINSERT(new User()));
    }

    @Test
    public void testQueryDELETE() {
        System.out.println(QueryHelper.createQueryDELETE(new User()));
    }
}
