# BALTIC NASDAQ SHARES API

### Project author: Karli Kullasepp.

# Read Me

This app creates an endpoint for getting Nasdaq Baltic share data. \
Request is sent to Nasdaq Baltic website for shares data at current date. \
Response, in HTML format, from the website is taken and share data is extracted from it. \
Extracted data is converted to entities and a response in JSON format will be returned. \
\
*localhost API UI url - http://localhost:8080/swagger-ui/index.html* \
NB! App relies on Nasdaq Baltic shares response's format remaining unchanged for it to work properly.
### JSON response:
[\
&nbsp; {\
&nbsp; &nbsp; "company": "Company name",\
&nbsp; &nbsp; "ticker": "Ticker",\
&nbsp; &nbsp; "lastPrice": 0.000,\
&nbsp; &nbsp; "change": 0.000,\
&nbsp; &nbsp; "percentage": 0.00,\
&nbsp; &nbsp; "bid": 0.000,\
&nbsp; &nbsp; "ask": 0.000,\
&nbsp; &nbsp; "trades": 0,\
&nbsp; &nbsp; "volume": 0,\
&nbsp; &nbsp; "turnover": 0.00\
&nbsp; },\
.\
.\
&nbsp; {\
...\
&nbsp; }\
]
