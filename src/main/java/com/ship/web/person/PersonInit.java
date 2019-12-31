package com.ship.web.person;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ship.web.proxy.Proxy;


@Component 
public class PersonInit extends Proxy implements ApplicationRunner{
	
	private PersonRepository personRepository;
	
	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	
	//databaseconfig 생성자 
	public PersonInit(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = personRepository.count();
		if(count == 0) {
			Person person = null;
		String[][] mtx = {{"a","1","Ddong","1999-11-11","1","3","2","100","student"},
				   {"hong","1111","홍길동","1993-12-11","1","2","3","90","student"},
				   {"you","1","유관순","1944-13-11","1","1","4","80","teacher"},
				   {"kim","1112","김유신","1959-32-11","1","3","5","70","student"},
				   {"bak","1","백두산","1951-32-11","0","2","1","60","student"},
				   {"you1","1","유재석","1952-32-11","1","1","2","50","student"},
				   {"hong1","1","홍사운드","1953-32-11","1","3","3","40","student"},
				   {"lee1","1","이상혁","1954-32-11","0","2","4","30","student"},
				   {"park1","1","박첨지","1955-32-11","1","1","5","20","student"},
				   {"bak1","1","백호","1956-32-11","1","3","1","10","student"},
				   {"you2","1","유화","1957-32-11","1","2","2","0","teacher"},
				   {"hong2","1","홍당무","1958-32-11","0","1","3","100","student"},
				   {"lee2","1","이사람","1950-32-11","1","3","4","99","student"},
				   {"park2","1","박술녀","1951-32-11","1","2","5","98","student"},
				   {"bak3","1","백김치","1952-32-11","0","1","1","87","student"},
				   {"kim3","1","김치녀","1953-32-11","1","3","2","97","student"},
				   {"lee3","1","이승엽","1954-32-11","1","2","3","95","student"},
				   {"park3","1","박호구","1922-32-11","0","3","4","95","student"},
				   {"hong4","1","홍씨","1923-32-11","1","2","5","93","student"},
				   {"lee4","1","이승엽","1924-32-11","1","1","6","91","student"},
				   { "park5", "1", "박지성",  "1981-06-05", "1", "1" ,"2","50","student"},
	               { "nfa", "1", "나도욱",  "1993-06-05", "0", "2" ,"3","35","teacher"},
	               { "mo", "1", "모모",  "1994-06-05", "0", "3" ,"4","87","student"},
	               { "sa", "1", "사나",  "1992-06-05", "0", "2" ,"1","42","student"},
	               { "ji", "1", "지효",  "1992-06-05", "0", "1" ,"2","98","teacher"},
	               { "da", "1", "다현",  "1994-06-05", "0", "3" ,"4","75","student"},
	               { "jj", "1", "쯔위",  "1996-06-05", "0", "2" ,"3","67","teacher"},
	               { "cy", "1", "채영",  "1999-06-05", "0", "1" ,"2","45","student"},
	               { "na", "1", "나연",  "1995-06-05", "0", "2" ,"1","82","student"},
	               { "jung", "1", "정연",  "1995-06-05", "0", "3" ,"3","68","student"},
	               { "mi", "1", "미나",  "1992-06-05", "0", "2" ,"2","47","student"}};
		for(String[] arr : mtx){
				person = new Person();
				person.setUserid(arr[0]);
				person.setPasswd(arr[1]);
				person.setName(arr[2]);
				person.setBirthday(df.parse(arr[3]));
				person.setMale(arr[4].equals("1"));
				person.setHak(integer(arr[5]));
				person.setBan(integer(arr[6]));
				person.setScore(integer(arr[7]));
				person.setRole(arr[8]);
				personRepository.save(person);
		  }
	   }
    }
 }

// for(int i=0; i< mtx.length; i++){
// 		person = new Person();
//		person.setUserid(mtx[i][0]);
//		person.setPasswd(mtx[1][1]);
//		person.setName(mtx[i][2]);
//		person.setBirthday(df.parse(mtx[i][3]));
//		personRepository.save(person);
// }
 
