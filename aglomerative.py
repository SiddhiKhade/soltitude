import numpy as np 
import matplotlib.pyplot as plt 
from scipy.cluster.hierarchy import dendrogram, linkage 
from sklearn.cluster import AgglomerativeClustering;
x=[0.40,0.22,0.35,0.26,0.08, 0.45] 
y=[0.53,0.38,0.32,0.19,0.41,0.30] 
data = list(zip(x,y)) 
linkage_data = linkage(data, method='complete', metric='euclidean') #change method to complete, average, single based on the question
dendrogram(linkage_data) 
plt.show()
#change method to complete, average, single based on the question & n_clusters based on the number of clusters
hierarchical_cluster = AgglomerativeClustering(n_clusters=2, affinity='euclidean', linkage='complete')
labels = hierarchical_cluster.fit_predict(data) 
plt.scatter(x,y, c=labels) 
plt.show()
