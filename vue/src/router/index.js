//import Vue from 'vue'
import Router from 'vue-router'
//import Home from '@/components/Home'
import Write from '@/components/board/Write'
import Remove from '@/components/board/Remove'
import Search from '@/components/board/Search'
import Update from '@/components/board/Update'
import List from '@/components/board/List'
import Join from '@/components/member/Join'
import Login from '@/components/member/Login'
import Mypage from '@/components/member/Mypage'
import MemberUpdate from '@/components/member/MemberUpdate'
import MemberList from '@/components/member/MemberList'

export default new Router({
	mode : 'history',
	routes : [
		//{path: '/', name: 'home', component: Home},
		{path: '/board/write', name: 'write', component: Write},
		{path: '/board/remove', name: 'remove', component: Remove},
		{path: '/board/search', name: 'search', component: Search},
		{path: '/board/update', name: 'update', component: Update},
		{path: '/board/list', name: 'list', component: List},
		{path: '/join', name: 'join', component: Join},
		{path: '/login', name: 'login', component: Login},
		{path: '/mypage', name: 'mypage', component: Mypage},
		{path: '/memberupdate', name: 'memberupdate', component: MemberUpdate},
		{path: '/memberlist', name: 'memberlist', component: MemberList}
	]
})