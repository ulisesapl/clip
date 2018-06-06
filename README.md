# clip
Repository for clip exercises 

## How to execute it
Execute the shell into the Clip directory as indicated below

### --For add transaction use:
	./application <user_id> add "<transaction_json>"
#### Example
  ./application 2 add "{\"amount\":1.23, \"description\": \"Joes Tacos\", \"date\":\"2018-12-30\", \"user_id\": 2 }"
##### Notes 
1. Don't forget to scape the double quotes.
2. The user_id into the request will be ignored. The transaction will be registred with the user_id passed as argument.
### --For show transaction use:
	./application <user_id> <transaction_id>
### --For list transanctions use:
	./application <user_id> list
### --For sum transactions user:
	./application <user_id> sum

