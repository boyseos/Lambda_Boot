<template>
<div>
	<div v-for="(item,key) of user" :key="key" type="text">
		{{key}}<input v-model="user[key]" value="user[key]" type="text" class="inputBox">
	</div>
	<input type="button" @click="updateuser" value="눌러"/>
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
		updateuser(){
			axios.post(`http://localhost:8080/updateuser/${this.user.userid}`
			,this.user
			,{'authorization': 'JWT fefege..',
			'Accept' : 'application/json',
			'Content-Type': 'application/json'
			}).then(res=>{
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
.inputBox {
	border: 1px solid #999;
}
</style>