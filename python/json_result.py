import csv
import json
csv_rows = []
with open('datafile.csv', 'r') as f:
    reader = csv.DictReader(f)
    title = reader.fieldnames
    for row in reader:
        csv_rows.extend([{title[i]:row[title[i]] for i in range(len(title))}])
with open("result2.json", "w") as f:
	f.write(json.dumps(csv_rows))