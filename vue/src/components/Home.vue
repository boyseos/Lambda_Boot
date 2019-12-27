<template>
<div id="app">
	<lay-out>
		<template #header="header">
			<h1>{{header.header}}</h1>
				<div v-if="loginState">
					<span v-for="navcommand of navcommands" :key="navcommand.name" class="header">
						<router-link :to="navcommand.path">{{navcommand.name}}</router-link>
					</span>
				</div>
				<a v-else @click="logout"><h2>로그아웃</h2></a>
		</template>
		<template #sidebar="sidebar">
			<ul class="menu">
				<component :is="auth()"/>
				<li v-for="sidebar of switchSideBar()" :key="sidebar.name">
					<router-link :to="sidebar.path">{{sidebar.name}}</router-link>
				</li>
			</ul>
		</template>
		<template #content="content">
			<h1>{{content.content}}</h1>
			<router-view/>
		</template>
		<template #footer="footer">
			<h1>{{footer.footer}}</h1>
		</template>
	</lay-out>
</div>
</template>
<script>
import {store} from '@/store'
import LayOut from '@/components/cmm/LayOut.vue'
import PreSideBar from '@/components/cmm/PreSideBar.vue'
import StudentSideBar from '@/components/cmm/StudentSideBar.vue'
import ManagerSideBar from '@/components/cmm/ManagerSideBar.vue'
export default({
	components : {LayOut,PreSideBar,StudentSideBar,ManagerSideBar},
	data(){
		return {
			loging: store.state.user.userid,
      navcommands:[
        {name: '로그인', path: '/login'},
        {name: '회원가입', path: '/join'}
      ]
		}
	},
	computed:{
		loginState: function(){
			return store.state.user.userid == undefined
		}
	},
	methods : {
		test(){
			alert(this.loging)
		},
		logout(){
			store.state.user = {}
			this.$router.push({path: '/login'})
		},
		auth(){
			switch (store.state.user.hak) {
				case undefined: return "PreSideBar"
				case 4: return "ManagerSideBar"
				default: return "StudentSideBar"
			} 
		},
		switchSideBar(){
			switch (store.state.user.hak) {
				case undefined: return undefined
				case 4: return [
					{name: '학생목록', path : '/memberlist'},
					{name: '학생수정', path : '/memberupdate'},
					{name: '학생입력', path : '/board/list'}]
				default: return [
					{name: '글쓰기', path : '/board/write'},
					{name: '목록', path : '/board/list'},
					{name: '글수정', path : '/board/update'},
					{name: '글삭제', path : '/board/remove'},
					{name: '검색', path : '/board/search'}]
			}
		}
	}
})
</script>
<style scoped>
ul.menu {
    position:relative;
    padding: 5px 5px 5px 5px;
    list-style: none;
    font-style: italic;
}
ul.menu a {
    text-decoration:none;
}
.header{
  text-align: right
}
</style>