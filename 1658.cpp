class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        int n = nums.size();
        
        int total = 0;
        for(int v : nums) total += v;

        int target = total - x;
        if(target < 0) return -1;
        if(target == 0) return n;

        int sum = 0, i = 0, maxLen = -1;

        for(int j = 0; j < n; j++) {
            sum += nums[j];

            while(sum > target) {
                sum -= nums[i];
                i++;
            }

            if(sum == target) {
                maxLen = max(maxLen, j - i + 1);
            }
        }

        if(maxLen == -1) return -1;
        return n - maxLen;
    }
};
