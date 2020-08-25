## Statement of goals
Nowadays, many social apps will match you with others based on some quiz. But we think these quizzes can hardly be used to predict one's appetite in making friends. So we are thinking about using the transaction history data to match people up. 

## Functional Description
1. E-commerce
	1. Identification module
		- Using spring default security architecture
		- May include location information
		- Normal user's account permission
			- default account will be granted buyer's permission
			- Basic permission: follow user, unfollow user, blacklist, chat
			- Buyer’s permission: browse the catalog, make the payment, request item, bid(?)
			- Bussiness’s permission: upload item, delete item
		- Admin user's account permission
			- Permission Admin: add permission, change permission, delete permission
			- User Admin: add user, change user’s data, delete user
			- Item Admin(could change items outside the business org): add Item, change Item’s data, delete Item
2. Social
	1. Recommend possible followers to users based on their purchase, view history; focus on some specific item(like books, CD)

## Frameworks & Tools

## User Interface

## System diagram

## Milestones

