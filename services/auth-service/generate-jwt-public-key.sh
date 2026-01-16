#!/usr/bin/env bash

set -euo pipefail

KEY_DIR="./secrets"
PRIVATE_KEY_PATH="$KEY_DIR/jwt-private.pem"

if [[ ! -f "$PRIVATE_KEY_PATH" ]]; then
  echo "Private key not found: $PRIVATE_KEY_PATH" >&2
  exit 1
fi

echo "Generating public key from: $PRIVATE_KEY_PATH"
echo

openssl pkey \
  -in "$PRIVATE_KEY_PATH" \
  -pubout
