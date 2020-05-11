# Inventory Management

## Requirements
### Assume that following requirement has been given to you and develop the software to meet these requirements.
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
"# inventory_management_engine" 
