package com.ship.web.proxy;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

@Component
public class Proxy {
	public int integer(String param) {
		Function<String, Integer> f = Integer :: parseInt;
		return f.apply(param);
	}
	public String string(Object param) {
		Function<Object, String> f = String :: valueOf;
		return f.apply(param);
	}
	public boolean equals(String p1, String p2) {
		BiPredicate<String, String> b = String :: equals;
		return b.test(p1, p2);
	}
	
	public int ranNum(int x, int y) {
		BiFunction<Integer, Integer, Integer> bf = (a,b) -> (int)Math.random()*b+a;
		return bf.apply(x, y);
	}
	public String ranEng(int x) {
		String result = "";
		for(int i = 0; i< x; i++) {
			result += String.valueOf((char) ((int) (new Random().nextInt(26)) + 65));
		}
		return result;
	}
	
	public int[] array(int size) {
		Function<Integer, int[]> f = int[] :: new;
		return f.apply(size);
	}
	public String currentDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}
	public String currentTime() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}
	public File makeDir(String t, String u) {
		BiFunction<String,String, File> f = File::new;
		return f.apply(t, u);
	}
	public File makeFile(File t, String u) {
		BiFunction<File,String, File> f = File::new;
		return f.apply(t, u);
	}
	
	
}


