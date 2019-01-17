#include <bits/stdc++.h>
using namespace std;
#define fori(i, st, en) for(int i = st; i < (int) en; i++)
#define rfori(i, st, en) for(int i = st; i >= (int) en; i--)
#define f first
#define s second
#define pii pair<int,int>
#define mp make_pair
#define pb push_back

int INFINITE = 0x3f3f3f3f;
string one, two;
int count1[100005];
int count2[100005];

int main(){
	cin.sync_with_stdio(0);
    cin.tie(0);
	freopen("input.txt", "r", stdin);
	cin >> one >> two;
	int size1 = one.size();
	for(int i = 0; i < size1; i++)
		count1[one[i]-'a']++;
	int size2 = two.size();
	for(int i = 0; i < size2; i++)
		count2[two[i]-'a']++;
	int total = 0;
	for(int i = 0; i < 26; i++){
		if(count2[i] < count1[i]){
			total += (count1[i] - count2[i]);
			count1[i] = count2[i];
		} else if(count2[i] > count1[i]){
			total += (count2[i] - count1[i]);
			count1[i] = count2[i];
		}
	}
	cout << total;
	return 0;
}