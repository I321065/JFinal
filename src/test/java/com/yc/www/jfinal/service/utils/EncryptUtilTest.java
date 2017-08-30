package com.yc.www.jfinal.service.utils;

import org.junit.*;

/** 
* EncryptUtil Tester. 
* 
* @author <Authors name> 
* @since <pre> 16, 2017</pre>
* @version 1.0 
*/ 
public class EncryptUtilTest {


@Before
public void before() throws Exception { 
    System.out.println("before...");
}

@After
public void after() throws Exception {
    System.out.println("after...");
} 

/** 
* 
* Method: md5Encode(String info) 
* 
*/ 
@Test
public void testMd5Encode() throws Exception { 
//TODO: Test goes here...
    Assert.assertNotEquals("fsf", EncryptUtil.md5Encode("md5"), "nd5");
} 

/** 
* 
* Method: shaEncode(String info) 
* 
*/ 
@Test
@Ignore
public void testShaEncode() throws Exception { 
//TODO: Test goes here...

} 


} 
