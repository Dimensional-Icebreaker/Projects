from selenium import webdriver
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.chrome.service import Service
from email.message import EmailMessage
import pandas as pd
import time
from datetime import datetime
import os
import sys
import ssl
import smtplib
import csv

def update_scan_count(filename_s):
    # Tracking the current scan count from the file
    try:
        with open(filename_s, 'r') as file:
            scan_count = int(file.read())
           # if (scan_count==11): #Making sure scan doesn't cross more than 10 times as the algorithm is designed to run for 10 times a day
             #   scan_count=0
    except FileNotFoundError:
        scan_count = 0

    # Increment the scan count
    scan_count += 1

    # Update the scan count in the file
    with open(filename_s, 'w') as file:
        file.write(str(scan_count))

    # Return the updated scan count
    return scan_count

def update_price_count(filename_s,slot_price):
    # Read the current best price from the file
    send=0 #send is either 1 or zero to track if the price is updated or not for each scan
    try:
        with open(filename_s, 'r') as file:
            best = int(file.read())
            if (best>slot_price):
                best= slot_price
                send=1
    except FileNotFoundError:
            best= slot_price
            send=1
    # Update the price in the file
    with open(filename_s, 'w') as file:
        file.write(str(best))

    # Return the updated scan count
    return send

filename_scan = 'scan_count.txt'
scan_count = update_scan_count(filename_scan)


script=os.path.dirname(sys.executable)
now=datetime.now()
date=now.strftime("%m_%d_%Y")
filepath = '/home/icebreaker/Documents/SCRAPY/headline_0{}-{}.csv'.format(scan_count,date)

website="https://www.kayak.com/flights/DFW-KTM/2023-12-17/2024-01-16?sort=price_a"
path="/home/icebreaker/Downloads/chromedriver"


service=Service(executable_path=path)
chrome_options = webdriver.ChromeOptions()
chrome_options.add_argument("--headless")
chrome_options.add_argument("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36")
chrome_options.add_experimental_option("detach", True)
driver = webdriver.Chrome(service=service, options=chrome_options)
driver.get(website)
driver.implicitly_wait(5)

Prices=[]
Airlines=[]
Links=[]


prices=driver.find_elements("xpath","//div[@class='f8F1-price-text']")
for price in prices:
    Prices.append(price.text)
cheap_slot=int(Prices[0].replace("$","").replace(",",""))
if (update_price_count("best.txt",cheap_slot)==1):
    airlines=driver.find_elements("xpath","//ol/li/div/div/div[@class='VY2U']/div[@class='c_cgF c_cgF-mod-variant-default']")
    for go,back in zip(airlines[::2],airlines[1::2]):
        Airlines.append("DFW-KTM: {} | KTM-DFW: {}".format(go.text,back.text).replace(","," and"))
    link= driver.find_elements("xpath","//div[@class='oVHK']/a[@role='link']")
    for l in link:
        Links.append(l.get_attribute("href"))

    my_dict={'Prices':Prices,'Airlines':Airlines,'Links':Links}
    headlines = pd.DataFrame(my_dict)
    final_path=os.path.join(script,filepath)
    headlines.to_csv(filepath)
    

    with open(filepath, 'a', newline='') as csvfile:
        writer = csv.writer(csvfile)

    csvfile.close()
    sender='dimensional.icebreaker@gmail.com'
    receiver='rxj9863@mavs.uta.edu'
    password='utnlcjqxxbbljfuz'
    subj="Update of plane tickets for {}[Scan Number:{}]".format(date.replace("_","/"),scan_count)
    body="""
    Welcome to Ribesh's Kitchen,
    It's my pleasure to serve you !
    >>>>>This order tracks the flight price 10 times a day<<<<<<<<< 
    >>>>>and sends you the order only if best price is detected<<<<<
    ----------------------------------
    Your order is ready!
    Best price is detected
    ----------------------------------
    """
    #utnlcjqxxbbljfuz
    em=EmailMessage()
    em['From']=sender
    em['To']=receiver
    em['Subject']=subj
    em.set_content(body)
    with open(filepath, 'rb') as file:
        em.add_attachment(file.read(), maintype='application', subtype='csv', filename='{}.csv'.format(date.replace("_","/"),scan_count))

    context=ssl.create_default_context()
    with smtplib.SMTP_SSL('smtp.gmail.com',465,context=context) as smtp:
        smtp.login(sender,password)
        smtp.sendmail(sender,receiver,em.as_string())

driver.quit()