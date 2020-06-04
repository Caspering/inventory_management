# Spring Boot Inventory Management System And e-checkout Counter

## Requirements

#### Online-Grocery store Admin:
- Create a back-end software to maintain Inventory of items in a general store.
- input for launching application: 3 csv files with list of fruits & Vegitables(f&v), processed food items & stationary.
- All the items have name, 15-digit barcode number, price & in-stock quantity
- processed food can be vegan or non-vegan. they have manufacturing & expiring dates
- Stationary is limited to only one item per type per purchase order.

#### Expected output(s):
- All data is to be persisted.
- REST API to add more quantity to stock (name/barcode & quantity)
- REST APIs for list of,
- all items in-stock with available quantity with details
- in-stock f&v with details
- in-stock processed food with details
- in-stock vegan processed food with details
- in-stock vegan processed food with details
- in-stock stationary with details

#### Add an e-checkout counter 

1.	Stationary is limited to one quantity per item type per customer purchase order.
2.	Purchase order will take:
•	date of purchase, 
•	list of items (should accept bar-code or name), 
•	quantity of each, 
•	payment type (cash or card), 
•	membership number(optional).
3.	Each purchase order will be assigned a receipt number on successful transaction.
4.	Inventory should be updated after each transaction.
5.	Maintain list of memberships with membership number, name, contact email & phone number.

#### Expected output(s):
####REST APIs for:
a.	Transaction details by receipt number.
b.	Receipt numbers by membership number
c.	Receipt numbers without membership number
d.	Total sale amount on a specific date or between two dates
e.	Total quantities of each item sold on a specific date or between two dates
f.	Log remaining quantities of items at every configurable number of minutes
g.	Add new member and generate membership number
h.	Search items by full/partial name/barcode 
i.	Maintain list of discounts on items. These should be applied at check-out and should be in receipt details
j.	List of suggested items based on previous purchases.
k.	Unit testing


#### Valid csv-File sample content:
##### F&v file:
|Name                                   | Barcode                               |Price      |qty|
|---------------------------------------|---------------------------------------|-----------|---|
|Banana                                 |123456789000001                        |0.99       | 15|

##### Processed food file:
|Name                                   |Barcode                                |Price      |qty         |type       |mfg                        |exp      |
|---------------------------------------|---------------------------------------|-----------|------------|-----------|---------------------------|---------|
|Potato chips                           |789000011                              |2.99       |5           |v          |3/20/2020                  |8/09/2020|

##### stationary:
|Name                                   |Barcode                                |Price      |qty|
|---------------------------------------|---------------------------------------|-----------|---|
|Glue stick                             |123456789000011                        |1.99       |15 |

