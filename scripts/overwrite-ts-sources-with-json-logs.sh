#!/bin/bash

# This script is used to maintain https://github.com/seratch/seratch-slack-types
# The npm package is an unofficial type definitions created by @seratch. It's NOT by Slack.

cd `dirname $0`/..
# git clone git@github.com:seratch/seratch-slack-types.git
cp -a json-logs/samples/audit/. ../seratch-slack-types/json/audit-api/
cp -a json-logs/samples/api/. ../seratch-slack-types/json/web-api/
cp -a json-logs/samples/events/. ../seratch-slack-types/json/events-api/
cp -a json-logs/samples/rtm/. ../seratch-slack-types/json/rtm-api/
cp -a json-logs/samples/scim/. ../seratch-slack-types/json/scim-api/
cp -a json-logs/samples/app-backend/dialogs/. ../seratch-slack-types/json/app-backend/dialogs/
cp -a json-logs/samples/app-backend/interactive-messages/. ../seratch-slack-types/json/app-backend/interactive-messages/
cp -a json-logs/samples/app-backend/message-actions/. ../seratch-slack-types/json/app-backend/message-actions/
cp -a json-logs/samples/app-backend/slash-commands/. ../seratch-slack-types/json/app-backend/slash-commands/
cp -a json-logs/samples/app-backend/views/. ../seratch-slack-types/json/app-backend/views/
echo "Done!"
