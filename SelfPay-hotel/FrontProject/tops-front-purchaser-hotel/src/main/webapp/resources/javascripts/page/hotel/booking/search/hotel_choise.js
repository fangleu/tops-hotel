function checkfrm(){
	var elm=document.getElementsByName('hotel_price')
	checked=false;
	for(ii=0;ii<elm.length;ii++) {
		if(elm[ii].checked){
			checked=true;
		}
	}
	if (!checked){
		alert('请选择价格');
	}
}
</script>