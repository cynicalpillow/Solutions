#include <bits/stdc++.h>
using namespace std;

int INFINITE = 0x3f3f3f3f;
int freq[1005];
vector<int> maxes;
vector<int> second_maxes;
int N, R;

int main(){
	cin >> N;
	int maxR = 0;
	memset(freq, 0, sizeof(freq));
	for(int i = 0; i < N; i++){
		cin >> R;
		freq[R]++;
		maxR = max(maxR, R);
	}
	//find max
	int running_max = -1;
	for(int i = 1; i <= maxR; i++){
		if(freq[i] > running_max && freq[i] > 0){
			maxes.clear();
			maxes.push_back(i);
			running_max = freq[i];
		} else if(freq[i] == running_max){
			maxes.push_back(i);
		}
	}
	if(maxes.size() > 1){
		sort(maxes.begin(), maxes.end());
		cout << abs(maxes[0]-maxes[maxes.size()-1]);
	} else {
		//find second max
		int second_running_max = -1;
		for(int i = 1; i <= maxR; i++){
			if(freq[i] == running_max)continue;
			if(freq[i] > second_running_max && freq[i] > 0){
				second_maxes.clear();
				second_maxes.push_back(i);
				second_running_max = freq[i];
			} else if(freq[i] == second_running_max){
				second_maxes.push_back(i);
			}
		}
		sort(second_maxes.begin(), second_maxes.end());
		sort(maxes.begin(), maxes.end());
		if(second_maxes.size() < 1)cout << abs(maxes[0]-maxes[maxes.size()-1]);
		else cout << max(abs(maxes[maxes.size()-1]-second_maxes[0]), abs(maxes[0]-second_maxes[second_maxes.size()-1]));
	}
	return 0;
}