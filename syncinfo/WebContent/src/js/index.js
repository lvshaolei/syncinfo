	var code;

	function createCode(){
		code="";
		var codeLength=4;
		var checkCode=document.getElementById("checkNode");
		var str="23QWERTYUIOPASDFGHJKLZXCVBNM1456789zxcvbnmasdfghjklqwertyuiop";
		for(var i=0;i<codeLength;i++){
			var charIndex=Math.floor(Math.random()*str.length);
			code += str[charIndex];
		}
		if (code.length != codeLength) {
			createCode();
		}
		checkCode.value=code;
	}