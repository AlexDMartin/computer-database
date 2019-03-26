$('#editform').on('submit', function() {
	$.ajax('computer/' + id, {
		method : 'PATCH',
		data : {
			id : id,
			computerName : computerName,
			introduced : introduced,
			discontinued : discontinued,
			companyId : companyId
		}
	})
})
