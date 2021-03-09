const name = document.getElementById('name')
const user = document.getElementById('user')
const email = document.getElementById('email')
const date = document.getElementById('date')
const pwd = document.getElementById('pwd')
const pw1 = document.getElementById('pwd1')
const form = document.getElementById('form')
const errorElement = document.getElementById('error')


form.addEventListener("submit", e =>{
	let messages = []
	if(name.value === '' || name.value == null){
		messages.push('Name is required')
	}
	if( name.value.length <= 4){
		messages.push('Name must be longer');
	}
	
	if(pwd.value.length < 8){
		messages.push('Password must be longer than 8 characters')
	}
	if(pwd.value.length > 20){
		messages.push('Password must be less than 20 characters')
	}
	
	if(pwd.value != pwd1.value){
		messages.push('password do not match')
	}
	if(date >= '2002-02-13'){
		messages.push('older than 18 years-old')
	}
	
	if(messages.length > 0){
		e.preventDefault()
		errorElement.innerText = messages.join(' , ')
	}
})