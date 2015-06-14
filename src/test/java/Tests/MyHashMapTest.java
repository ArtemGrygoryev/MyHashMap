package Tests;

import com.artem.myhashmap.MyHashMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MyHashMapTest {
    @Test
    public void containsKeyShouldHandleEmptyMap() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        assertFalse(myHashMap.containsKey("яблоко"));
    }

    @Test
    public void containsValueShouldHandleEmptyMap() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        assertFalse(myHashMap.containsValue("фрукт"));
    }

    @Test
    public void putShouldHandleEmptyMap() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        myHashMap.put("яблоко", "фрукт");
        assertTrue(myHashMap.containsKey("яблоко"));
        assertTrue(myHashMap.containsValue("фрукт"));
    }

    @Test
    public void putShouldHandleSameKey() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        myHashMap.put("яблоко", "фрукт");
        myHashMap.put("яблоко", "плод");
        assertTrue(myHashMap.containsKey("яблоко"));
        assertTrue(myHashMap.containsValue("плод"));
        assertFalse(myHashMap.containsValue("фрукт"));
    }

    @Test
    public void putShouldHandleResize() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        for (int i = 0; i < 20; i++) {
            myHashMap.put(i, i);
        }
        for (int i = 0; i < 20; i++) {
            assertTrue(myHashMap.containsKey(i));
            assertTrue(myHashMap.containsValue(i));
        }
    }

    @Test
    public void putShouldHandleNull() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        myHashMap.put(null, null);
        assertTrue(myHashMap.containsKey(null));
        assertTrue(myHashMap.containsValue(null));
    }

    @Test
    public void getShouldHandleEmptyMap() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        assertEquals(myHashMap.get(10), null);
    }

    @Test
    public void getShouldHandleNull() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        myHashMap.put(null, "яблоко");
        assertEquals(myHashMap.get(null), "яблоко");
    }

    @Test
    public void getShouldHandleNotNull() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        myHashMap.put("яблоко", "фрукт");
        assertEquals(myHashMap.get("яблоко"), "фрукт");
    }

    @Test
    public void sizeShouldHandleEmptyMap() {
        MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
        assertEquals(myHashMap.size(), 0);
    }

    @Test
    public void sizeShouldHandleNotEmptyMap() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        for (int i = 0; i < 20; i++) {
            myHashMap.put(i, i);
        }
        assertEquals(myHashMap.size(), 20);
    }

    @Test
    public void isEmptyShouldHandleEmptyMap() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        assertTrue(myHashMap.isEmpty());
    }

    @Test
    public void isEmptyShouldHandleNotEmptyMap() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        myHashMap.put(null, null);
        assertFalse(myHashMap.isEmpty());
    }

    @Test
    public void removeShouldHandleOneNode() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        myHashMap.put(null, null);
        myHashMap.remove(null);
        assertEquals(myHashMap.size(), 0);
    }

    @Test
    public void removeShouldHandleNonexistentKey() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        myHashMap.put(null, null);
        myHashMap.remove(10);
        assertEquals(myHashMap.size(), 1);
    }

    @Test
    public void removeShouldHandleNull() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        myHashMap.put(null, null);
        myHashMap.put(10, 10);
        myHashMap.remove(null);
        assertEquals(myHashMap.size(), 1);
        assertFalse(myHashMap.containsKey(null));
    }

    @Test
    public void removeShouldHandleNotNull() {
        MyHashMap<Integer, Integer> myHashMap = new MyHashMap<Integer, Integer>();
        myHashMap.put(null, null);
        myHashMap.put(10, 10);
        myHashMap.remove(10);
        assertEquals(myHashMap.size(), 1);
        assertFalse(myHashMap.containsKey(10));
    }
}
