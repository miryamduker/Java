package test;

import com.company.dao.DaoFileImpl;
import com.company.dm.DataModel;
import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class DaoFileImplTest {
    @Test
    public void daoFileTest() throws FileNotFoundException {
        DataModel<String> entity1 = new DataModel<String>(1L,"aa");
        DataModel<String> entity2 = new DataModel<String>(2L,"bb");
        DataModel<String> entity3 = new DataModel<String>(3L,"cc");

        DaoFileImpl<String> hardisk = new DaoFileImpl<String>("source");
        hardisk.save(entity1);
        System.out.println(entity1.toString());
        hardisk.save(entity2);
        Assert.assertEquals(null,hardisk.find(4L));
        hardisk.save(entity3);
        Assert.assertEquals("cc",hardisk.find(3L).getContent());
        entity3.setContent("dd");
        hardisk.save(entity3);

    }


}
