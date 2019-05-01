const oracledb = require("oracledb");
const express = require("express");
const bodyParser = require("body-parser");
const uuid = require("uuid");


const app = express();

// Setting up body-parser
app.use(bodyParser.urlencoded({extended : true}));
app.use(bodyParser.json());

oracledb.autoCommit = true;

let connection;

async function run() {
    connection =  await oracledb.getConnection(
        {
            user: "vishal",
            password: "password",
            connectString: process.env.LOCAL
        }
    );
}

// Category table
const TABLE_CATEGORIES = "categories";
const COL_CATEGORY_ID = "category_id";
const COL_CATEGORY = "category";
const COL_MONTHLY_PAYMENT = "monthlyPayment"

app.get('/api/categories', function(req, res){
    query = `select * from ${TABLE_CATEGORIES} order by ${COL_CATEGORY}` ;
    console.log(query);
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            
            categories = [];
            cols = [COL_CATEGORY_ID, COL_CATEGORY, COL_MONTHLY_PAYMENT];
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                categories.push(obj);
            });
            res.json(categories);
          });
})

const TABLE_SERVICE_PROVIDERS = 'serviceProviders'; 
app.get('/api/providers/:city&:id', function(req, res){
    const city = req.params.city;
    const c_id = req.params.id;
    query = `select * from ${TABLE_SERVICE_PROVIDERS} where city = '${city}' and category_id = '${c_id}' order by rating desc` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            
            providers = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                providers.push(obj);
            });
            res.json(providers);
          });
})

app.get('/api/providers/:id', function(req, res){
    const c_id = req.params.id;
    query = `select * from ${TABLE_SERVICE_PROVIDERS} where user_id = '${c_id}'` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            
            providers = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                providers.push(obj);
            });
            res.json(providers);
          });
})

app.get('/api/providers/', function(req, res){
    const city = req.params.city;
    query = `select * from ${TABLE_SERVICE_PROVIDERS} order by rating desc` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            
            providers = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                providers.push(obj);
            });
            res.json(providers);
          });
})

const TABLE_USERS = 'users'; 
app.get('/api/users', function(req, res){
    query = `select * from ${TABLE_USERS}` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            
            users = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                users.push(obj);
            });
            res.json(users);
          });
})

app.get('/api/users/:id', function(req, res){
    const id = req.params.id;
    query = `select * from ${TABLE_USERS} where user_id = '${id}'`;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            
            users = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                users.push(obj);
            });
            res.json(users);
          });
})

const TABLE_SERVICES = 'services'; 
const TABLE_SERVICE_LINKS = 'serviceLinks';
const COL_SERVICE_ID = 'service_id';
const COL_PROVIDER_ID = 'provider_id';
app.get('/api/services/:id', function(req, res){
    const provider_id = req.params.id;
    query = `select * from ${TABLE_SERVICES} 
            inner join ${TABLE_SERVICE_LINKS} 
            on ${TABLE_SERVICES}.${COL_SERVICE_ID} = ${TABLE_SERVICE_LINKS}.${COL_SERVICE_ID}
            where ${TABLE_SERVICE_LINKS}.${COL_PROVIDER_ID} = '${provider_id}'` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            services = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                services.push(obj);
            });
            res.json(services);
          });
})

app.get('/api/services/', function(req, res){
    const provider_id = req.params.id;
    query = `select * from ${TABLE_SERVICES}` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) { 
                console.error(err); 
                return; 
            }
            services = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                services.push(obj);
            });
            res.json(services);
          });
})

app.post("/api/order", (req, res) => {
    const mUser_id = req.body.user_id;
    const mService_id = req.body.service_id;
    const mStart_time = parseInt(req.body.start_time);
    const mDate_of_order = parseInt(req.body.date_of_order);
    const mDuration = parseInt(req.body.duration);
    const mOrder_id = uuid.v4();
    const mCompleted = 0;
    const mDeleted = 0; 

    query = "BEGIN make_order(:order_id, :user_id, :service_id, :date_of_order, :start_time, :duration, :completed, :deleted); END;";

    connection.execute(query, {
        order_id: mOrder_id,
        user_id: mUser_id,
        service_id: mService_id,
        date_of_order: mDate_of_order,
        start_time: mStart_time,
        duration: mDuration,
        completed: mCompleted,
        deleted: mDeleted
    }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});        
    })
}); 

const TABLE_ORDERS = 'orders';
const COL_USER_ID = 'user_id';
app.get('/api/orders/:id', function(req, res){
    const user_id = req.params.id;
    query = `select * from ${TABLE_ORDERS} inner join ${TABLE_SERVICES} on ${TABLE_ORDERS}.${COL_SERVICE_ID} = ${TABLE_SERVICES}.${COL_SERVICE_ID} where ${TABLE_ORDERS}.${COL_USER_ID} = '${user_id}' order by date_of_order desc` ;
    console.log(query);
    connection.execute(query, {}, (err, result) =>
          {
            if (err) {
                console.error(err); 
                res.send({"status": false}); 
                return; 
            }
            orders = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                orders.push(obj);
            });
            res.json(orders);
          });
})


app.get('/api/orders/', function(req, res){
    const user_id = req.params.id;
    query = `select * from ${TABLE_ORDERS}` ;
    connection.execute(query, {}, (err, result) =>
          {
            if (err) {
                console.error(err); 
                res.send({"status": false}); 
                return; 
            }
            orders = [];
            cols = [];
            result.metaData.forEach(col => {
                cols.push(col.name);
            });
            len = cols.length;
            result.rows.forEach(row => {
                obj = {};
                for(i = 0; i < len; i++) {
                    obj[cols[i]] = row[i];
                }
                orders.push(obj);
            });
            res.json(orders);
          });
})


// Work In Progress
const COL_START_TIME = "start_time";
const COL_ORDER_ID = "order_id";
app.post("/api/completeOrders/:order_id", (req, res) => {
    const mOrder_id = req.params.order_id;
    const mRating = req.body.rating;

    query = "BEGIN complete_order(:order_id, :rating); END;";

    connection.execute(query, {
        order_id: mOrder_id,
        rating: mRating
    }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});        
    })
    // START HERE (MAKE A PUT REQUEST(TO SET DELETED = 1) AND A POST REQUEST)
   
});

app.put("/api/deleteOrders/:order_id", (req, res) => {
    const mOrder_id = req.params.order_id;

    query = "BEGIN set_delete_order(:order_id); END;";

    connection.execute(query, { order_id: mOrder_id }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});        
    })   
});

const COL_PASSWORD = 'password';
app.post("/api/user/login", (req, res) => {
    const user_id = req.body.user_id;
    const password = req.body.password;
    query = `select * from ${TABLE_USERS} where ${COL_USER_ID} = '${user_id}' and ${COL_PASSWORD} = '${password}'`;
    console.log(query)
    connection.execute(query, { }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : result.rows.length == 1});        
    })   
});

app.post("/api/user/checkUser", (req, res) => {
    const user_id = req.body.user_id;
    query = `select * from ${TABLE_USERS} where ${COL_USER_ID} = '${user_id}'`;
    console.log(query)
    connection.execute(query, { }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : result.rows.length == 1});        
    })   
});

app.post("/api/providers/login", (req, res) => {
    const user_id = req.body.user_id;
    const password = req.body.password;
    query = `select * from ${TABLE_SERVICE_PROVIDERS} where ${COL_USER_ID} = '${user_id}' and ${COL_PASSWORD} = '${password}'`;
    console.log(query)
    connection.execute(query, { }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : result.rows.length == 1});        
    })   
});

app.post("/api/providers/checkUser", (req, res) => {
    const user_id = req.body.user_id;
    query = `select * from ${TABLE_SERVICE_PROVIDERS} where ${COL_USER_ID} = '${user_id}''`;
    console.log(query)
    connection.execute(query, { }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": "error"}); 
            return; 
        }
        res.send({"status" : result.rows.length == 1});        
    })   
});


app.post("/api/users/register", (req, res) => {
    const muser_id = req.body.user_id;
    const mpassword = req.body.password;
    const memail = req.body.email;
    const mfirstName = req.body.firstName;
    const mlastName = req.body.lastName;
    const mgender = req.body.gender; 
    const mage = req.body.age;
    const mcontactNo = req.body.contactNo;
    const maddLine1 = req.body.addLine1;
    let maddLine2;
    if (req.body.addLine2) {
        maddLine2 = req.body.addLine2;
    } else {
        maddLine2 = '';
    }
    const mcity = req.body.city;

    query = "BEGIN insert_user(:user_id, :password, :email, :firstName, :lastName, :gender, :age, :contactNo, :addLine1, :addLine2, :city); END;";
    connection.execute(query, {
        user_id: muser_id, 
        password: mpassword, 
        email: memail, 
        firstName: mfirstName, 
        lastName: mlastName, 
        gender:mgender, 
        age:mage, 
        contactNo: mcontactNo, 
        addLine1: maddLine1, 
        addLine2: maddLine2, 
        city: mcity
     }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});        
    })   
});

app.post("/api/providers/register", (req, res) => {
    const muser_id = req.body.user_id;
    const mpassword = req.body.password;
    const memail = req.body.email;
    const mcompanyName = req.body.companyName;
    const mcontactNo = req.body.contactNo;
    const mcategory_id = req.body.category_id;
    const maddLine1 = req.body.addLine1;
    let maddLine2;
    if (req.body.addLine2) {
        maddLine2 = req.body.addLine2;
    } else {
        maddLine2 = '';
    }
    const mcity = req.body.city;

    console.log(muser_id);
    query = "BEGIN insert_provider(:user_id, :password, :email, :companyName, :contactNo, :category_id, :addLine1, :addLine2, :city); END;";
    connection.execute(query, {
        user_id: muser_id, 
        password: mpassword, 
        email: memail, 
        companyName: mcompanyName,
        contactNo: mcontactNo, 
        category_id: mcategory_id,
        addLine1: maddLine1, 
        addLine2: maddLine2, 
        city: mcity
     }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});        
    })   
});

const TABLE_ADMINS = "admins";
app.post("/api/admins/login", (req, res) => {
    const user_id = req.body.user_id;
    const password = req.body.password;
    query1 = `select * from ${TABLE_ADMINS} inner join ${TABLE_USERS} on ${TABLE_ADMINS}.${COL_USER_ID} = ${TABLE_USERS}.${COL_USER_ID} where ${TABLE_USERS}.${COL_USER_ID} = '${user_id}' and ${TABLE_USERS}.${COL_PASSWORD} = '${password}'`;
    connection.execute(query1, { }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : result.rows.length == 1});     
    });
});

app.put("/api/changePassword/:id", (req, res) => {
    const mPassword = req.body.password;
    const mId = req.params.id;
    query = "BEGIN change_password(:id, :pass); END;";
    connection.execute(query, {
        id: mId,
        pass: mPassword
     }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});     
    });
});

app.put("/api/provider/:id", (req, res) => {
    const mUser_id = req.params.id;
    const mPassword = req.body.password;
    const mEmail = req.body.email;
    const mCompanyName = req.body.companyName;
    const mContactNo = req.body.contactNo;
    const mAddLine1 = req.body.addLine1;
    const mAddLine2 = req.body.addLine2;
    const mCity = req.body.city;

    query = "BEGIN update_provier(:id, :pass, :mail, :cName, :cNo, :aLine1, :aLine2, :c); END;";
    connection.execute(query, {
        id: mUser_id,
        pass: mPassword,
        mail: mEmail,
        cName: mCompanyName,
        cNo: mContactNo,
        aLine1: mAddLine1,
        aLine2: mAddLine2,
        c: mCity
     }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});     
    });
});

app.delete("/api/provider/:id", (req, res) => {
    const mId = req.params.id;

    query = "BEGIN delete_provider(:id); END;";
    connection.execute(query, { id: mId }, (err, result) => {
        if (err) {
            console.error(err); 
            res.send({"status": false}); 
            return; 
        }
        res.send({"status" : true});     
    });
});



run();

const PORT = process.env.PORT || 5000;

app.listen(PORT, () => console.log(`Server started on port ${PORT}`));