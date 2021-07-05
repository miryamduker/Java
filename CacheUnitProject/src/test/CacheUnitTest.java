package test;

import com.company.dm.DataModel;
import com.company.memory.CacheUnit;
import org.junit.Assert;
import org.junit.Test;
import project1.LRUAlgoCacheImpl;

import java.util.Arrays;

public class CacheUnitTest {
    @Test
    public void cacheUnitTest() {
        LRUAlgoCacheImpl<Long, DataModel<String>> lru = new LRUAlgoCacheImpl<Long, DataModel<String>>(4);

        CacheUnit<String> cache = new CacheUnit<String>(lru);
        DataModel<String>[] dataArray = new DataModel[4];
        dataArray[0] = new DataModel<String>(1L, "aa");
        dataArray[1] = new DataModel<String>(2L, "bb");
        dataArray[2] = new DataModel<String>(3L, "cc");
        dataArray[3] = new DataModel<String>(4L, "dd");
        cache.putDataModels(dataArray);

        Long ids[] = new Long[3];
        ids[0] = 1l;
        ids[1] = 2l;
        ids[2] = 3l;

        DataModel<String>[] returnedData = cache.getDataModels(ids);
        DataModel<String>[] newArray = Arrays.copyOfRange(dataArray, 0, 3);

        Assert.assertArrayEquals(returnedData, newArray);
        DataModel[] newData = new DataModel[1];

        newData[0] = new DataModel<String>(5L, "ee");
        Assert.assertEquals(dataArray[3], cache.putDataModels(newData)[0]);
    }

}
