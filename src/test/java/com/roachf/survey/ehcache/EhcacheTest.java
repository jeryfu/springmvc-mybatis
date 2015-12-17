package com.roachf.survey.ehcache;

import net.sf.ehcache.CacheManager;

public class EhcacheTest {
	public static void main(String[] args) {
		CacheManager cacheManager = CacheManager.create("ehcache.xml");
		cacheManager.addCache("test");
	}
}
