<template>
<div id="wrapper">
	<header>
		<slot name="header" :header="authcheck"/>
	</header>
	<aside id="sidebar">
		<slot name="sidebar"/>
	</aside>
	<section id="content">
		<slot name="content" :content="content">
          </slot>
	</section>
	<footer>
		<slot name="footer" :footer="footer"/>
	</footer>
</div>
</template>
<script>
export default({
	data(){
		return {
			content : '으음.',
               footer : '흐음.',
               sidebar : null
               
		}
     },
     computed:{
          authcheck(){
               switch(this.$store.state.role){
                    case 'student': return `${this.$store.state.user.name} 학생`
                    case 'teacher': return `${this.$store.state.user.name} 슨상님`
                    default: return '게스트'
               }
          }
     }
})
</script>
<style scoped>
#wrapper {
     padding: 5px;
     width: 960px;
     margin: 20px auto;
}
header {
     height: 100px;
     padding: 0 15px;
}
#content {
     width: 696px;
     float: left;
     padding: 5px 15px;
    min-height: 300px;
}
#sidebar {
     width: 100px;
     padding: 5px 15px;
     float: left;
}
footer {
     clear: both;
     padding: 0 15px;
}
/* 가로폭 980보다 작을 때 사용할 @media query */
@media screen and (max-width: 980px) {     
     #pagewrap {
           width: 94%;
     }
     #content {
           clear: both;
           padding: 1% 4%;
           width: auto;
           float: none;
     }
     #sidebar {
           clear: both;
           padding: 1% 4%;
           width: auto;
           float: none;
     }
     header, footer {
           padding: 1% 4%;
     }
}
/* 공통 */
#content {
     background: #f8f8f8;
}
#sidebar {
     background: #f0efef;
}
header, #content, #middle, #sidebar {
     margin-bottom: 5px;
}
#pagewrap, header, #content, #middle, #sidebar, footer {
     border: solid 1px #ccc;
}
</style>