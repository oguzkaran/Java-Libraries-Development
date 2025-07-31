#!/bin/bash

r=${1:-1}

all_passed=true
check_mark="✓"
cross_mark="✗"

echo -e "\033[36mRunning 'mvn clean test'...\033[0m"
echo -n "Test Rounds: "

start_time=$(date +%s)

for ((i=1; i<=r; i++)); do
    output=$(mvn clean test 2>&1)
    exit_code=$?

    if [ $exit_code -eq 0 ]; then
        echo -ne "\033[32m[$check_mark] \033[0m"
    else
        echo -ne "\033[31m[$cross_mark] \033[0m"
        echo -e "\n\033[33mRound #$i failed. Maven [ERROR] output:\033[0m"
        echo "$output" | grep --color=always "\[ERROR\]"
        all_passed=false
        break
    fi
done

end_time=$(date +%s)
elapsed=$((end_time - start_time))
elapsed_formatted=$(printf "%02d:%02d:%02d" $((elapsed/3600)) $(( (elapsed%3600)/60 )) $((elapsed%60)))

if [ "$all_passed" = true ]; then
    echo -e "\n\033[32m[All $r runs completed successfully. No tests failed.]\033[0m"
fi
echo -e "\033[36mTotal time elapsed: ${elapsed_formatted}\033[0m"