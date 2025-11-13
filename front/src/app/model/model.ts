export interface Category {
  id: number;
  name: string;
  status: number;
}

export interface Status {
  id: number;
  name: string;
}

export interface Priority {
  id: number;
  name: string;
  status: number;
}

export interface Activity {
  id: number;
  name: string;
  description: string;
  id_category: Category;
  id_status: Status;
  id_priority: Priority;
  status: number;
  created_at: string;
  updated_at: string;
}

export interface ActivityNew {
  name: string;
  description: string;
  id_category: number;
  id_status: number;
  id_priority: number;
}