# BALTIC NASDAQ SHARES API

### Project author: Karli Kullasepp.

# Read Me

This app creates an endpoint for getting Nasdaq Baltic share data. \
Request is sent to Nasdaq Baltic website for shares data at current date. \
Response, in HTML format, from the website is taken and share data is extracted from it. \
Extracted data is converted to entities and a response in JSON format will be returned. \
\
NB! App relies on Nasdaq Baltic shares response's format remaining unchanged for it to work properly. \
\
*Default localhost API UI url - http://localhost:8080/swagger-ui/index.html* \
\
Request URL: http://localhost:8080/nasdaq \
\
Response example: \
[\
&nbsp; {\
&nbsp; &nbsp; "company": "String",\
&nbsp; &nbsp; "ticker": "String",\
&nbsp; &nbsp; "lastPrice": 0,\
&nbsp; &nbsp; "change": 0,\
&nbsp; &nbsp; "percentage": 0,\
&nbsp; &nbsp; "bid": 0,\
&nbsp; &nbsp; "ask": 0,\
&nbsp; &nbsp; "trades": 0,\
&nbsp; &nbsp; "volume": 0,\
&nbsp; &nbsp; "turnover": 0\
&nbsp; }\
]
