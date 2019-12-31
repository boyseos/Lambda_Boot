//import loginAPI from '@/api/loginAPI'
import axios from 'axios'
import Constant from './mutation_types'

const state = {person: {}}
const getters = {getperson: state => state.person}
const actions = {
	async login(userid,passwd){
		alert('연결')
		axios.post('http://localhost:8080/login',
			{userid:userid,passwd:passwd},
			{'authorization': 'JWT fefege..',
			'Accept' : 'application/json',
			'Content-Type': 'application/json'
	}).then(res=>{
		if(res.data.msg ==="Success"){
		this.$store.commit(Constant.USER,res.data.person) 
		this.$store.commit(Constant.IS_AUTH, true)
		alert(`스토어에 저장성공  ${this.$store.state.userid}`)
		this.$router.push({path: (res.data.person.role == 'teacher') ? '/memberlist' : '/mypage'})
		}else{
		alert('로그인 실패')
		this.$router.push({path:'/login'})
		}
	})
	.catch(()=>{
		alert('AXIOS 실패')
	})
	},
	async join(){
		axios.post('http://localhost:8080/join',
		{userid:this.userid,passwd:this.passwd,name:this.name},
		{'authorization': 'JWT fefege..',
			'Accept' : 'application/json',
			'Content-Type': 'application/json'
		}).then(()=>{
			alert('성공')
		}).catch(()=>{
			alert('실패')
		})
	}
}
const mutations = {
	LOGIN (state, person){
		state.person = person
	},
	LOGOUT (state){
		state.person = null
	}
}

export default {
	namespaced: true,
	state,
	getters,
	actions,
	mutations
}
