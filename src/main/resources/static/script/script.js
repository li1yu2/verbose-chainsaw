/**
 * 
 */
	function timeout(){
	const msg = document.querySelector('#msg')
			setTimeout(function(){
				msg.innerHTML=''
			},5000)		
}
	function salaryValidBegin(){
				var salaryValue=salaryBegin.value
				
				var regxSalary = /^\d+(\.\d+)?$/
				if (salaryValue.match(regxSalary)||salaryValue.trim()==='') {
			        return true
			    }else{
					 Msg.innerHTML='誤っている給料を入力している。'
					 setTimeout(function(){
						Msg.innerHTML=''
					 },5000)
					 return false
				}
				}
	function salaryValidEnd(){
				var salaryValue=salaryEnd.value
				
				var regxSalary = /^\d+(\.\d+)?$/
				if (salaryValue.match(regxSalary)||salaryValue.trim()==='') {
			        return true
			    }else{
					 Msg.innerHTML='誤っている給料を入力している。'
					 setTimeout(function(){
						 Msg.innerHTML=''
					 },5000)
					 return false
				}
				}
	function dateValidBegin(){
				var dateValue=dateBegin.value
				var regxDate = /^(18|19|20)\d{2}\/(0[1-9]|1[0-2])\/(0[1-9]|[12]\d|3[01])$/
				
				if (dateValue.match(regxDate)||dateValue.trim()==='') {
			        return true
			      }else{
					 Msg.innerHTML='誤っている日付を入力している。'
					 setTimeout(function(){
						 Msg.innerHTML=''
					 },5000)
					 return false
				  }
				  }
	function dateValidEnd(){
				var dateValue=dateEnd.value
				var regxDate = /^(18|19|20)\d{2}\/(0[1-9]|1[0-2])\/(0[1-9]|[12]\d|3[01])$/
				
				if (dateValue.match(regxDate)||dateValue.trim()==='') {
			        return true
			      }else{
					 Msg.innerHTML='誤っている日付を入力している。'
					 setTimeout(function(){
						 Msg.innerHTML=''
					 },5000)
					 return false
					}
					}

		let weeklist = ['日', '月', '火', '水', '木', '金', '土']
		function getJapTime() {
     	let date = new Date()
      	let year = date.getFullYear()
      	let month = date.getMonth() + 1
      	let day = date.getDate()
      	let week = date.getDay()
      	let h = date.getHours()
      	let m = date.getMinutes()
      	let s = date.getSeconds()

      	month = month < 10 ? '0' + month : month
      	day = day < 10 ? '0' + day : day
      	h = h < 10 ? '0' + h : h
      	m = m < 10 ? '0' + m : m
      	s = s < 10 ? '0' + s : s

      	return `${year}年${month}月${day}日 ${weeklist[week]}曜日 ${h}時${m}分${s}秒`
    }
				

