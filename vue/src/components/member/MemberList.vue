<template>
  <div>
	<select v-model="role" style="border : solid 1px;">
		<option value="teacher" selected>슨상님</option>
		<option value="student">학상</option>
	</select>
	<input v-model="searchword" type="text" style="border : solid 1px;"/>
	<input @click="find" type="button" value="검색해라"><br />
	<a @click.prevent="lambdapractice('namesOfStudents')">1. 학생의 이름</a><br />
	<a @click.prevent="lambdapractice('streamToArray')">2. 스트림을 배열로 전환</a><br />
	<a @click.prevent="lambdapractice('streamToMap')">3. 스트림을 맵으로 전환. 학생이름이  key</a><br />
	<a @click.prevent="lambdapractice('theNumberOfStudents')">4. 학생의 수</a><br />
	<a @click.prevent="lambdapractice('totalScore')">5. 학생들 총점</a><br />
	<a @click.prevent="lambdapractice('topStudent')">6. 1등 학생</a><br />
	<a @click.prevent="lambdapractice('getStat')">7. 전체 학생 성적 통계</a><br />
	<a @click.prevent="lambdapractice('nameList')">8. 전체 학생 이름</a><br />
	<a @click.prevent="lambdapractice('partioningBy')">1.단순분할 (성별로 분할)</a><br />
	<a @click.prevent="lambdapractice('partioningCountPerGender')">2.단순분할 (성별 학생수)</a><br />
	<a @click.prevent="lambdapractice('partioningTopPerGender')">3.단순분할 (성별 1등)</a><br />
	<a @click.prevent="lambdapractice('partioningRejectPerGender')">4.다중분할 (성별 불합격자,  50점이하)</a><br />
	<a @click.prevent="lambdapractice('groupingByBan')">1. 단순 그룹화(반별로 그룹화)</a><br />
	<a @click.prevent="lambdapractice('groupingByGrade')">2. 단순그룹화(성적별로 그룹화)</a><br />
	<a @click.prevent="lambdapractice('groupingByCountByLevel')">3. 단순그룹화 + 통계(성적별 학생수))</a><br />
	<a @click.prevent="lambdapractice('groupingByHakAndBan')">4. 다중 그룹화 (학년별, 반별)</a><br />
	<a @click.prevent="lambdapractice('groupingTopByHakAndBan')">5. 다중 그룹화 +통계 (학년별, 반별  1등)</a><br />
	<a @click.prevent="lambdapractice('groupingByStat')">6. 다중그룹화 + 통계(학년별, 반별  성적그룹)</a><br />
	<div v-for="(item,index) of list" :key="item.id">
		<div v-if="index==0" class="table">
			<div v-for="column of Object.keys(item)" :key="column" @click="sort(column)">{{column}}{{sorted[column] ? "↓":"↑"}}</div>
		</div>
		<div class="table">
			<div v-for="(item2,index2) of item" :key="index2">
				{{index2 == 'male' ? (item2 ? "남" : "여") : item2}}
			</div>
		</div>
	</div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
	created(){
	},
	data(){
		return {
			list : {},
			sorted : {},
			role : '',
			searchword : ''
		}
	},
	methods : {
		listview(item){
			let result = ""
			Object.keys(item).map(i => {
				result += (`${i} =	${item[i]}`)
			})
			return result
		},
		sort(column){
			this.sorted[column] = !this.sorted[column]
			if(this.sorted[column]){
				this.list.sort((a, b) => {
					return a[column] > b[column] ? 1 : a[column] < b[column] ? -1 : 0;
				})
			}
			else{
				this.list.sort((a, b) => {
					return a[column] > b[column] ? -1 : a[column] < b[column] ? 1 : 0;
				})
			}
		},
		find(){
			axios.get(`http://localhost:8080/list/${this.role}/${this.searchword === '' ? 'a' : this.searchword}`
			).then(res=>{
				this.list = res.data.list
				alert((res.data.msg) ? "Success" : "fail")
			}).catch(()=>{
				alert(`${this.role}/${this.searchword}`)
			})
		},
		topByGrade(){
			this.searchword = 'topByGrade'
		},
		lambdapractice(searchword){
			this.searchword = searchword
			axios.get(`http://localhost:8080/practice/${searchword}`
			).then(res=>{
				this.list = res.data
			}).catch(()=>{
				alert(`${searchword}`)
			})
		}
	}
}
</script>
<style scoped>
.table{
	display: grid; grid-template-columns: 9% 9% 9% 12% 9% 9% 9% 9% 9% 16%;
	border: solid 1px;
	padding: 2px;
}
</style>
