# SocialEshopOnMicroservices
[![CircleCI](https://circleci.com/gh/vercel/micro/tree/master.svg?style=shield)](https://circleci.com/gh/vercel/micro/tree/master)
[![Install Size](https://packagephobia.now.sh/badge?p=micro)](https://packagephobia.now.sh/result?p=micro)
![Gradle Package](https://github.com/ycpi/SocialEshopOnMicroservices/workflows/Gradle%20Package/badge.svg)

## Statement of goals
Nowadays, many social apps will match you with others based on some quiz. But we think these quizzes can hardly be used to predict one's appetite in making friends. So we are thinking about using the transaction history data to match people up. 

## Functional Description
1. E-commerce
   	- Backend logic: 
	1. Identification module
		- Database: 
            1. Normal user: ID|UserName|Password|Email|Address|Phone|Payment
            2. Bussiness user: ID|UserName|Password|Email|Address|Phone|Company
            3. Admin user: ID|UserName|Password
		
		- Security:
  		    - Using spring default security architecture
  		    - OATH 2(login with 3rd party service account)
        
		- Permission:
    		- Normal user's account permission
    			- Social permission: follow user, unfollow user, blacklist, chat
    			- Buyer’s permission: browse the catalog, make the payment, request item, buy item
    		- Bussiness user's account permission
        		- Seller's permission: add item information, delete item information, publish item information, edit item information
    		- Admin user's account permission
        		- Certification: certify bussiness account application
    			- Item Admin(could change items outside the business org): delete Item
  			 
     	- Function:
			1. Provide authentication and verification for users 
   
      	- API design:(Endpoint: auth/path)
      	  
		| Method | Path             | Description           | User Authenticated | Available from UI |
		|--------|------------------|-----------------------|--------------------|-------------------|
		| POST   | /signup          | Normal user Sign up   | X                  |                   |
		| POST   | /signup/business | Business user sign up | X                  |                   |
		| POST   | /login           | log in                | X                  |                   |
					 
    1. Basket module
        - Database:
			ID|Owner_ID|Item_ID|Count
		- Function:
			1. CRUD 
			2. empty all
			3. Buy item

	2. Ordering module
		- Database:
			ID|Owner_ID|Item_ID|Count|Order_time|Price
		- Function:
			1. CR 

    4. Catalog module
		- Database:
			ID|name|cost|amount|Description
		- Function:
			1. CRUD
			2. add item to basket 
   
   - FrontEnd logic:
	3. Login\Signup page:
		call function in Identification service
	3. Profile page:
		call function in Baskets,Ordering services

2. Social
	4. Recommend possible followers to users based on their purchase, view history; focus on some specific item(like books, CD)

## Frameworks & Tools
1. Front-End
	| Tools       | Info                               |
	|-------------|------------------------------------|
	| Vue         | Front\-end framework, MVVM pattern |
	| Element\-UI | Vue\.js based UI framework         |

2. Back-End
	| Tools      | Info                |
	|------------|---------------------|
	| Spring     | Back\-end framework |
	| PostgreSQL | databases\(SQL\)    |

3. DevOps
	| Tools   | Info                                                                    |
	|---------|-------------------------------------------------------------------------|
	| Docker  | containers                                                              |
	| Kubernetes| open\-source platform for managing containerized workloads and services |
	| Jenkins | CI/CD                                                                   |

## User Interface

## System diagram

## Milestones

