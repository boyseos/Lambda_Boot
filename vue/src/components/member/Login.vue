<template>
  <div id="login">
  <v-container>
    <v-layout row class="text-xs-center">
      <v-flex xs3 style="background-image: url('http://cdn.wallpapersafari.com/7/86/gqiGH7.jpg')">
        <!-- <v-card height="500px"></v-card> -->
      </v-flex>
      <v-flex xs4 class="grey lighten-4">
        <v-container style="position: relative;top: 13%;" class="text-xs-center">
          <v-card flat>
            <v-card-title primary-title>
              <h4>Login</h4>
            </v-card-title>
            <v-form>
            <v-text-field v-model="userid" prepend-icon="person" name="Username" label="Username"></v-text-field>
            <v-text-field v-model="passwd" prepend-icon="lock" name="Password" label="Password" type="password"></v-text-field>
            <v-card-actions>
              <v-btn @click.prevent="login" primary large block>Login</v-btn>
            </v-card-actions>
            </v-form>
          </v-card>
        </v-container>
      </v-flex>
    </v-layout>
  </v-container>
</div>
</template>

<script>
import { mapMutations } from 'vuex'
//import {store} from '@/store'
export default {
  data(){
    return {
      userid : '',
      passwd : '',
      result : [],
    }
  },
  methods : {
    ...mapMutations({
      LOGIN: 'LOGIN'
    }),
    login(userid,passwd){
      this.$store.dispatch('person/login', (userid,passwd))
    }
  }
}
/* import store from 'store'
export default{
	data(){
		return {
			context : 'http://localhost:8080/',
			result : '',
			userid : '',
			passwd : '',
		}
	},
	methods : {
		login(){
			alert(`${this.userid} ${this.passwd}`)
			axios
			.post(`${this.context}/login`,{userid : this.userid,passwd : this.passwd},
			{
				'authorization':'JWT fefege..',
				'Accept' : 'application/json',
				'Content-Type':'application/json'
			})
			.then(res=>{
			if(res.data.result === 'Success'){
				alert(`로그인 성공 ${this.userid} ${this.passwd}`)
				store.state.loginedUid = this.userid
				store.state.loginedPwd = this.passwd
				alert(`스토어에 저장 성공 ${store.state.loginedUid}`)
			}else{
				alert('로그인 실패')
			}
			})
			.catch(()=>{
				alert('axios 실패')
			})
		}
	}
} */
</script>
<style scoped>
</style>
