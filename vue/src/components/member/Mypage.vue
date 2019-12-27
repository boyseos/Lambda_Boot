<template>
<div>
	<h3 @click="userupdate(index)" v-for="(item,index) of user" :key="item">{{index}} : {{item}}</h3>
	<h2 @click="withdraw">회원탈퇴</h2>
</div>
</template>
<script>
import {store} from '@/store'
import axios from 'axios'
export default({
	data(){
		return {
			user : store.state.user
		}
	},
	methods:{
		withdraw(){
			alert('회원탈퇴')
			axios.delete(`http://localhost:8080/withdraw/${this.user.userid}`
			).then(res=>{
				alert((res.data) ? "Success" : "fail")
				this.$router.push({path:'/login'})
			}).catch(()=>{
				alert('axois 실패')
			})
		}
	}
})
</script>
<style scoped>
</style>
