function displayUsers(users) {
    var $tbody = $('tbody');
    $tbody.empty();
    for(var i in users) {
        var user = users[i];
        var $row = $('<tr>');
        $('<td>').html(user.username).appendTo($row);

        $row.appendTo($tbody);
    }
}

function refreshUsers() {
    $.get('/users', {}, function(result) {
        displayUsers(result);
    });
}

function addUser(user) {
    $.ajax('/createUsers', {
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        type: 'POST',
        data: JSON.stringify(user),
        dataType: 'json',
        success: function(result) {
            $('#error').val(result.message);
            refreshUsers();
            $('#username, #password').val('');
        },
    });
}

function deleteUser(username) {
     $.ajax('/deleteUsers', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: username,
            dataType: 'json',
            success: function(result) {
                $('#error').val(result.message);
                refreshUsers();
                $('#deleteUsername').val('');
            }
        });
}


function updateUser(user) {
     $.ajax('/updateUsers', {
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: 'POST',
            data: JSON.stringify(user),
            dataType: 'json',
            success: function(result) {
                $('#error').val(result.message);
                refreshUsers();
                $('#updateUsername, #updatePassword').val('');
            }
     });
}

$(function() {
    refreshUsers();
    $('button').click(function() {
        if (this.id == "createUser") {
            addUser({
                'username':     $('#username').val(),
                'password':   $('#password').val()
            });
        };
        if (this.id == "deleteUser") {
            deleteUser({
                'username':     $('#deleteUsername').val()
            })
        };
        if (this.id == "updateUser") {
            updateUser({
                'username':     $('#updateUsername').val(),
                'password':   $('#updatePassword').val()
            })};
        return false;
    });
});