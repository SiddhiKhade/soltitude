#TE3_22_Siddhi Khade
#Naive Bayesian Classification
from collections import Counter
import pandas as pd

probY = dict()
probN = dict()
TY = 0 #total number of yes
TN = 0 #total number of no
def count(singlelist, combinelist):
    x = dict(Counter(tuple(i) for i in combinelist.tolist()))
    for i in x:
        if i[1] == 'yes':
            probY[i] = x[i]/TY
        else:
            probN[i] = x[i]/TN
# Importing the dataset
dataset = pd.read_csv("AllElectronics.csv")

target = -1
targetdata = dataset.iloc[:,target].values
temp = dict(Counter(targetdata.tolist()))
for i in temp:
    if(i == 'no'):
        TN = temp[i]
    else:
        TY = temp[i]

datalen = len(targetdata)
PY = TY/datalen
PN = TN/datalen

for i in range(1,len(dataset.columns)-1):
    count(dataset.iloc[:, i].values,dataset.iloc[:, [i, target]].values)
    inpt =[]
for j in range(1,len(dataset.columns)-1):
    inpt.append(input(f"Enter {dataset.columns[j]} ({set(dataset.iloc[:,j].values.tolist()) } ): "))

AY = PY
for i in probY:
    if i[0] in inpt:
        AY = AY * probY[i]
AN = PN
for i in probN:
    if i[0] in inpt:
        AN = AN * probN[i]

AY,AN = AY/(PY+PN),AN/(PY+PN)
print("\n\n")

if(AY>AN):
    print(dataset.columns[target],": Yes - ",AY)
else:
    print(dataset.columns[target],": No - ",AN)