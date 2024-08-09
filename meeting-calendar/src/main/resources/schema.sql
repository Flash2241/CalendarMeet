CREATE TABLE Reminders (
                           reminder_id SERIAL PRIMARY KEY,
                           meeting_id INTEGER REFERENCES Meetings(meeting_id),
                           reminder_time TIMESTAMPTZ
);

CREATE TABLE MeetingChanges (
                                change_id SERIAL PRIMARY KEY,
                                meeting_id INTEGER REFERENCES Meetings(meeting_id),
                                old_title VARCHAR(100),
                                old_start_time TIMESTAMPTZ,
                                old_end_time TIMESTAMPTZ,
                                creator_id INTEGER REFERENCES Users(user_id),
                                old_job_id INTEGER REFERENCES Jobs(job_id),
                                old_meeting_result VARCHAR,
                                change_time TIMESTAMPTZ,
                                operation VARCHAR(20)
);

CREATE TABLE ParticipantsChanges (
                                     change_id SERIAL PRIMARY KEY,
                                     meeting_id INTEGER REFERENCES Meetings(meeting_id),
                                     user_id INTEGER REFERENCES Users(user_id),
                                     old_status_id INTEGER REFERENCES MeetingParticipantsStatus(meeting_status_id),
                                     change_time TIMESTAMPTZ,
                                     operation VARCHAR(20)
);
//