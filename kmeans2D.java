// to run:  javac kmeans2D.java then java kmeans2D.java

import java.util.*;
import java.lang.Math.*;
class Main
{
Scanner sc = new Scanner (System.in);
int noe, noc, flag;
int ptr[] = new int[100];
double centroid[][] = new double[100][2];
double oldCentroid[][] = new double[100][2];
static double elements[][] = new double[100][2];
double k[][][] = new double[100][100][2];
double dist (double x1, double y1, double x2, double y2)
{
return Math.sqrt ((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
}
void insert () throws InvalidClusterSize
{
System.out.print ("\nK-Means Clustering using euclidean distance");
System.out.print ("\nEnter number of Clusters: ");
noc = sc.nextInt ();
System.out.print ("Enter number of points: ");
noe = sc.nextInt ();
System.out.println ("\nEnter Points");
for (int i = 0; i < noe; i++)
{
System.out.print ("Enter point " + (i + 1) + ": ");
elements[i][0] = sc.nextDouble ();
elements[i][1] = sc.nextDouble ();
}
System.out.println ("\nEnter Centroid");
for (int i = 0; i < noc; i++)
{
System.out.print ("Enter Centroid " + (i + 1) + ": ");
centroid[i][0] = sc.nextDouble ();
centroid[i][1] = sc.nextDouble ();
}
}
void solve ()
{
int clusterIndex = flag = 0;
double leastDiff, tempDist;
for (int i = 0; i < noc; i++)
{
ptr[i] = 0;
}
for (int i = 0; i < noe; i++)
{
leastDiff = dist (elements[i][0], elements[i][1], centroid[0][0],
centroid[0][1]);
clusterIndex = 0;
for (int j = 0; j < noc; j++)
{
tempDist = dist (elements[i][0], elements[i][1], centroid[j][0],
centroid[j][1]);
if (tempDist < leastDiff)
{
leastDiff = tempDist;
clusterIndex = j;
}
}
k[clusterIndex][ptr[clusterIndex]][0] = elements[i][0];
k[clusterIndex][ptr[clusterIndex]][1] = elements[i][1];
ptr[clusterIndex]++;
}
for (int i = 0; i < noc; i++)
{
centroid[i][0] = centroid[i][1] = 0;
for (int j = 0; j < ptr[i]; j++)
{
centroid[i][0] += k[i][j][0];
centroid[i][1] += k[i][j][1];
}
centroid[i][0] /= ptr[i];
centroid[i][1] /= ptr[i];
}
for (int i = 0; i < noc; i++)
{
if ((oldCentroid[i][0] != centroid[i][0]) || oldCentroid[i][1] !=
centroid[i][1])
{
flag = 1;
break;
}
}
if (flag == 1)
{
for (int i = 0; i < noc; i++)
{
oldCentroid[i][0] = centroid[i][0];
oldCentroid[i][1] = centroid[i][1];
}
solve ();
}
}
void disp ()
{
for (int i = 0; i < noc; i++)
{
System.out.println ("\nCluster " + (i + 1) + ":");
for (int j = 0; j < ptr[i]; j++)
{
System.out.println (k[i][j][0] + ", " + k[i][j][1]);
}
System.out.printf ("Centroid %d = (%.2f, %.2f)\n", (i + 1),
centroid[i][0], centroid[i][1]);
}
}
public static void main (String args[])
{
try
{
Main km = new Main ();
km.insert ();
km.solve ();
km.disp ();
} catch (InvalidClusterSize e)
{
System.out.println ("Exception occured: " + e);
}
}
}
class InvalidClusterSize extends Exception
{
public InvalidClusterSize (String s)
{
super (s);
}
}