$(document).ready(function () {

    $('#editform').validate({ 
        rules: {
        	computerName: {
                required: true,
            },
            introduced: {
            	required: false,
            },
            discontinued: {
            	required: false,
            },
            companyId: {
                required: true
            }
        },
        messages: {
        	computerName: "Please specify a computer name",
        	introduced: "Please enter a valid date",
        	discontinued: "Please enter a valid date"
        }
    });
  
});