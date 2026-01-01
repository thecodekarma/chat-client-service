#!/bin/bash
set -e

if [ -z "${OPENAI_API_KEY}" ]; then
  echo "Error: OPENAI_API_KEY is not set"
  exit 1
fi

echo "Calling Open AI..."
PROMPT="What is Spring AI"

curl https://api.openai.com/v1/chat/completions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer ${OPENAI_API_KEY}" \
  -d "{\"model\":\"gpt-5\",\"messages\":[{\"role\":\"user\",\"content\":\"${PROMPT}\"}]}"