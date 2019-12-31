package com.ship.web.person;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ship.web.proxy.Proxy;
import com.ship.web.proxy.Trunk;
import com.ship.web.util.Printer;

@RestController
@CrossOrigin
public class PersonController extends Proxy{
	@Autowired private PersonRepository personRepository;
	@Autowired private Person person;
	@Autowired Trunk<Object> trunk;
	@Autowired private Printer printer;
	@Autowired ModelMapper modelMapper;
	@Autowired PersonService personService;
	@Bean public ModelMapper modelMapper() {return new ModelMapper();}
	
	@RequestMapping("/")
	public String index() {
		Iterable<Person> all = personRepository.findAll();
		StringBuilder sb = new StringBuilder();
		all.forEach(p -> sb.append(p.getName() + " "));
		return sb.toString();
	}
	
	@PostMapping("/join")
	public void join(@RequestBody Person param){
		printer.accept("회원가입 진입 !!!");
		printer.accept("회원가입 아이디" +param.getUserid());
		
		for(int i = 0; i<300; i++) {
			param.setBan(ranNum(1,9));
			param.setBirthday(new Date());
			param.setMale(ranNum(1,2)==1);
			param.setHak(ranNum(1,4));
			param.setName(ranEng(3));
			param.setPasswd("1");
			param.setScore(ranNum(1,99));
			param.setUserid(ranEng(2));
			personRepository.save(param);
		}
	}
	@DeleteMapping("/withdraw/{userid}")
	public boolean withdraw(@PathVariable String userid){
		printer.accept("회원탈퇴 진입");
		printer.accept("넘어온 아이디 "+ userid);
		person = personRepository.findByUserid(userid);
		personRepository.delete(person);
//		trunk.put(Arrays.asList("msg"), Arrays.asList("success"));
//		return trunk.get();
		return person == null;
	}
	@PostMapping("/login")
	public Map<String,Object> login(@RequestBody Person param) {
		HashMap<String,Object> map = new HashMap<>();
		printer.accept("로그인 진입 !!");
		printer.accept(String.format("ID : %s", param.getUserid()+""));
		printer.accept(String.format("PASSWD : %s", param.getPasswd()+""));
		person = personRepository.findByUseridAndPasswd(param.getUserid(), param.getPasswd());
		if(person != null) {
			printer.accept("로그인성공");
			map.put("msg", "Success");
			map.put("person", person);
		}else {
			printer.accept("로그인 실패");
			map.put("msg", "fail");
			map.put("person", person);
		}
		
//		person.setUserid(person.getUserid()+"rmsgh");
//		person.setPasswd(person.getPasswd()+"1234");
		return map;
	}
	
	@PostMapping("/updateuser/{userid}")
	public Map<?, ?> updateUser(@PathVariable String userid, @RequestBody Person param){
		System.out.println(param);
		personRepository.save(param);
		trunk.put("msg", "Success");
		return trunk.get();
	}
	
	@GetMapping("/list/{role}/{search}")
	public Map<?, ?> list(@PathVariable String role, @PathVariable String search){
		Iterable<Person> entites = personRepository.findAll();
		System.out.println(search);
		switch (search) {
			case "topByGrade" : entites = personRepository.findGroupByHak(); 
				break;
			default:break;
		}
		List<Person> list = new ArrayList<>();
		for(Person p : entites) {
			Person dto = modelMapper.map(p,Person.class);
			list.add(dto);
		}
		trunk.put(Arrays.asList("msg","list"),
				Arrays.asList("Success",list.stream()
						.filter(i -> i.getRole().equals(role))));
		return trunk.get();
	}
	
	@GetMapping("/practice/{search}")
	public Object practice(@PathVariable String search){
		System.out.println(search);
		switch (search) {
			case "namesOfStudents" : return personService.namesOfStudents();
			case "streamToArray" : return personService.streamToArray();
			case "streamToMap" : return personService.streamToMap();
			case "theNumberOfStudents" : return personService.theNumberOfStudents();
			case "totalScore" : return personService.totalScore();
			case "topStudent" : return personService.topStudent();
			case "getStat" : return personService.getStat();
			case "nameList" : return personService.nameList();
			case "partioningByGender" : return personService.partioningByGender(true);
			case "partioningCountPerGender" : return personService.partioningCountPerGender(true);
			case "partioningTopPerGender" : return personService.partioningTopPerGender(true);
			case "partioningRejectPerGender" : return personService.partioningRejectPerGender(true);
			case "findByHak" : return personService.findByHak();
			case "groupByGrade" : return personService.groupByGrade();
			case "personCountByLevel" : return personService.personCountByLevel();
			case "multiGrouping" : return personService.multiGrouping();
			case "multiGroupingMax" : return personService.multiGroupingMax();
			case "multiGroupingGrade" : return personService.multiGroupingGrade();
			default:
				break;
		}
		return null;
	}
}
